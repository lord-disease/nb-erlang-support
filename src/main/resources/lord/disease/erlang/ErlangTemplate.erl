-module(dynamic_adspace_handler).
-behaviour(cowboy_http_handler).

-export([init/3]).
-export([handle/2]).
-export([terminate/3]).
-export([genJS/0]).

-record(state, {
}).

-define(JSDIR,"/home/alain/NetBeansProjects/ssp/static/js/").
-define(DYNAMIC,"localhost:8080").
-define(LOG,"localhost:8080").
-define(ADVSTLIB,"components/advstlib/advstlib.min.js").
-define(ADVSTLIBPATH,"static/js/" ++ ?ADVSTLIB).
-define(ADVSTHASH,hashFile(?JSDIR ++ ?ADVSTLIB)).
-define(ADJSR,"adjs_r.js").
-define(ADJSRPATH,"static/js/" ++ ?ADJSR).
-define(ADJSRHASH,hashFile(?JSDIR ++ ?ADJSR)).

hashFile(Path) ->
	{ok, Bin} = file:read_file(Path),
	Data=binary_to_list(Bin),
	<<Md5:128/big-unsigned-integer>>=erlang:md5(Data),
	Fstring=io_lib:format("~32.16.0b", [Md5]),
	lists:flatten(Fstring).


genJS() ->
    receive
	{From, QsVals, Ua} ->
	    {ok, Content} = 'adjs_r.js_dtl':render([
			{advstparams, jiffy:encode(advstParam(QsVals, Ua))},
			{uriauthority, ?DYNAMIC},
			{advstlib_file, ?ADVSTLIBPATH},
			{adjsr_file, ?ADJSRPATH},
			{advstlib_hash, ?ADVSTHASH},
			{adjsr_hash, ?ADJSRHASH}
		]),
	    From ! Content
    after 40 ->
	    timeout
    end.

init(_, Req, _Opts) ->
	Pid = spawn(?MODULE, genJS, []),
	{AllValues, _} = cowboy_req:qs_vals(Req),
	{HeaderVal, _} = cowboy_req:header(<<"user-agent">>,Req),
    Pid ! {self(), AllValues, HeaderVal},
    receive
		timeout ->
		    {ok, cowboy_req:reply(503, Req), _Opts};
		Msg ->
		    {ok, cowboy_req:reply(200, [{<<"content-type">>, <<"text/plain">>}], Msg, Req), _Opts}
	    after 50 ->
		    {ok, cowboy_req:reply(503, Req), _Opts}
    end.

handle(Req, State=#state{}) ->
	{ok, Req2} = cowboy_req:reply(200, Req),
	{ok, Req2, State}.

terminate(_Reason, _Req, _State) ->
	ok.

advstParam(QsVals, Ua) ->
    {[
      {zoneid, list_to_binary(zoneId(QsVals))},
	  {extid, list_to_binary(extId(QsVals))},
      {width, 0},
      {height, 0},
      {k, null},
      {k2, null},
      {'uri.authority', list_to_binary(?DYNAMIC)},
      {'uri.log.authority', list_to_binary(?LOG)},
      {bannerid, null},
      {'ad3.browser.name', uaParser(browser, name, Ua)},
      {'ad3.browser.version', uaParser(browser, version, Ua)},
      {'ad3.os', uaParser(os, family, Ua)}
     ]}.

uaParser(Type, Prop, Ua) -> 
    proplists:get_value(Prop, proplists:get_value(Type, uaparser:parse(Ua))).

extId(QsVals) ->
    extId(find, lists:keyfind(<<"extid">>, 1, QsVals)).

extId(find, Param) when Param =/= false -> 
    {_, Extid} = Param, {Out, _}= binary_to_list(Extid), Out;
extId(find, _) -> 
    "0".

zoneId(QsVals) ->
    zoneId(lists:keyfind(<<"what">>, 1, QsVals),
	   lists:keyfind(<<"zone">>, 1, QsVals),
	   lists:keyfind(<<"zoneid">>, 1, QsVals)).

zoneId(Param, _, _) when Param =/= false -> 
    {_, "zone:" ++ Zone} = Param, Out= binary_to_list(Zone), Out;
zoneId(_, Param, _) when Param =/= false -> 
    {_, Zone} = Param,
	binary_to_list(Zone);
zoneId(_, _, Param) when Param =/= false -> 
    {_, Zone} = Param,
	binary_to_list(Zone);
zoneId(_, _, _) -> 
    "0".

% lulz wat